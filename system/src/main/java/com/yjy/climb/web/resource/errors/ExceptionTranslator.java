package com.yjy.climb.web.resource.errors;

import java.net.URI;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import com.yjy.climb.exception.BadRequestAlertException;
import com.yjy.climb.exception.management.EmailAlreadyUsedException;
import com.yjy.climb.exception.ErrorConstants;
import com.yjy.climb.exception.FieldErrorVM;
import com.yjy.climb.exception.auth.InvalidPasswordException;
import org.apache.commons.lang3.StringUtils;
import org.zalando.problem.DefaultProblem;
import org.zalando.problem.Problem;
import org.zalando.problem.ProblemBuilder;
import org.zalando.problem.Status;
import org.zalando.problem.StatusType;
import org.zalando.problem.spring.web.advice.ProblemHandling;
import org.zalando.problem.spring.web.advice.security.SecurityAdviceTrait;
import org.zalando.problem.spring.web.advice.validation.ConstraintViolationAdviceTrait;
import org.zalando.problem.violations.ConstraintViolationProblem;
import org.zalando.problem.violations.Violation;
import tech.jhipster.config.JHipsterConstants;
import tech.jhipster.web.util.HeaderUtil;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.NativeWebRequest;

import static com.yjy.climb.exception.ErrorConstants.ERR_MSG;

/**
 * Controller advice to translate the server side exceptions to client-friendly json structures.
 * The error response follows RFC7807 - Problem Details for HTTP APIs (https://tools.ietf.org/html/rfc7807).
 */
@ControllerAdvice
public class ExceptionTranslator implements ProblemHandling, SecurityAdviceTrait, ConstraintViolationAdviceTrait {

    private static final String FIELD_ERRORS_KEY = "fieldErrors";
    private static final String MESSAGE_KEY = "message";
    private static final String PATH_KEY = "path";
    private static final String VIOLATIONS_KEY = "violations";

    @Value("${spring.application.name}")
    private String applicationName;

    private final Environment env;

    public ExceptionTranslator(Environment env) {
        this.env = env;
    }

    /**
     * Post-process the Problem payload to add the message key for the front-end if needed.
     */
    @Override
    public ResponseEntity<Problem> process(@Nullable ResponseEntity<Problem> entity, NativeWebRequest request) {
        if (entity == null) {
            return null;
        }
        Problem problem = entity.getBody();
        if (!(problem instanceof ConstraintViolationProblem || problem instanceof DefaultProblem)) {
            return entity;
        }

        HttpServletRequest nativeRequest = request.getNativeRequest(HttpServletRequest.class);
        String requestUri = nativeRequest != null ? nativeRequest.getRequestURI() : StringUtils.EMPTY;
        ProblemBuilder builder = Problem
            .builder()
            .withStatus(problem.getStatus())
            .withTitle(problem.getTitle())
            .with(PATH_KEY, requestUri);

        if (problem instanceof ConstraintViolationProblem) {
            builder
                .with(VIOLATIONS_KEY, ((ConstraintViolationProblem) problem).getViolations())
                .with(MESSAGE_KEY, ErrorConstants.ERR_VALIDATION);
        } else {
            builder.withCause(((DefaultProblem) problem).getCause()).withDetail(problem.getDetail()).withInstance(problem.getInstance());
            problem.getParameters().forEach(builder::with);
            if (!problem.getParameters().containsKey(MESSAGE_KEY) && problem.getStatus() != null) {
                builder.with(MESSAGE_KEY, "error.http." + problem.getStatus().getStatusCode());
            }
        }
        return new ResponseEntity<>(builder.build(), entity.getHeaders(), entity.getStatusCode());
    }

	@ExceptionHandler
	public ResponseEntity<Problem> handleNullPointException(NullPointerException ex, NativeWebRequest request){
		Problem problem = Problem
				.builder()
				.withTitle(ERR_MSG)
				.withStatus(defaultConstraintViolationStatus())
				.build();
		return create(ex, problem, request);
	}

	/**
	 * Spring MVC缺少参数返回的错误
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler
	@Override
	public ResponseEntity<Problem> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, NativeWebRequest request){
		Problem problem = Problem
				.builder()
				.withTitle("参数校验失败")
				.withStatus(defaultConstraintViolationStatus())
				.build();
		return create(ex, problem, request);
	}

	@ExceptionHandler
	@Override
	public ResponseEntity<Problem> handleConstraintViolation(ConstraintViolationException exception, NativeWebRequest request) {
		List<Violation> violations = exception.getConstraintViolations().stream().map(this::createViolation).toList();
		Problem problem = Problem
				.builder()
				.withTitle("参数校验失败")
				.with(VIOLATIONS_KEY, violations)
				.withDetail("error.validation")
				.withStatus(defaultConstraintViolationStatus())
				.build();
		return create(exception, problem, request);
	}

	@ExceptionHandler
	public ResponseEntity<Problem> handleRuntimeException(RuntimeException ex, NativeWebRequest request){
		Problem problem = Problem
				.builder()
				.withTitle(ERR_MSG)
				.withStatus(defaultConstraintViolationStatus())
				.build();
		return create(ex, problem, request);
	}



    @Override
    public ResponseEntity<Problem> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, @Nonnull NativeWebRequest request) {
        BindingResult result = ex.getBindingResult();
        List<FieldErrorVM> fieldErrors = result
            .getFieldErrors()
            .stream()
            .map(f ->
                new FieldErrorVM(
                    f.getObjectName().replaceFirst("DTO$", ""),
                    f.getField(),
                    StringUtils.isNotBlank(f.getDefaultMessage()) ? f.getDefaultMessage() : f.getCode()
                )
            )
            .collect(Collectors.toList());

        Problem problem = Problem
            .builder()
            .withType(ErrorConstants.CONSTRAINT_VIOLATION_TYPE)
            .withTitle("方法参数无效")
            .withStatus(defaultConstraintViolationStatus())
            .with(MESSAGE_KEY, ErrorConstants.ERR_VALIDATION)
            .with(FIELD_ERRORS_KEY, fieldErrors)
            .build();
        return create(ex, problem, request);
    }

    @ExceptionHandler
    public ResponseEntity<Problem> handleEmailAlreadyUsedException(
        EmailAlreadyUsedException ex,
        NativeWebRequest request
    ) {
        EmailAlreadyUsedException problem = new EmailAlreadyUsedException();
        return create(
            problem,
            request,
            HeaderUtil.createFailureAlert(applicationName, true, problem.getEntityName(), problem.getErrorKey(), problem.getMessage())
        );
    }


    @ExceptionHandler
    public ResponseEntity<Problem> handleInvalidPasswordException(
       InvalidPasswordException ex,
        NativeWebRequest request
    ) {
        return create(new InvalidPasswordException(), request);
    }

    @ExceptionHandler
    public ResponseEntity<Problem> handleBadRequestAlertException(BadRequestAlertException ex, NativeWebRequest request) {
		Problem problem = Problem
				.builder()
				.withTitle(ex.getTitle())
				.withStatus(defaultConstraintViolationStatus())
				.withDetail(String.format("error.%s.%s", ex.getEntityName(), ex.getErrorKey()))
				.build();
		return create(ex, problem, request);
    }

    @ExceptionHandler
    public ResponseEntity<Problem> handleConcurrencyFailure(ConcurrencyFailureException ex, NativeWebRequest request) {
        Problem problem = Problem.builder().withStatus(Status.CONFLICT).with(MESSAGE_KEY, ErrorConstants.ERR_CONCURRENCY_FAILURE).build();
        return create(ex, problem, request);
    }

    @Override
    public ProblemBuilder prepare(final Throwable throwable, final StatusType status, final URI type) {
        Collection<String> activeProfiles = Arrays.asList(env.getActiveProfiles());

        if (activeProfiles.contains(JHipsterConstants.SPRING_PROFILE_PRODUCTION)) {
            if (throwable instanceof HttpMessageConversionException) {
                return Problem
                    .builder()
                    .withTitle(status.getReasonPhrase())
                    .withStatus(status)
                    .withDetail(ERR_MSG)
                    .withCause(
                        Optional.ofNullable(throwable.getCause()).filter(cause -> isCausalChainsEnabled()).map(this::toProblem).orElse(null)
                    );
            }
            if (throwable instanceof DataAccessException) {
                return Problem
                    .builder()
                    .withTitle(status.getReasonPhrase())
                    .withStatus(status)
                    .withDetail("数据访问失败")
                    .withCause(
                        Optional.ofNullable(throwable.getCause()).filter(cause -> isCausalChainsEnabled()).map(this::toProblem).orElse(null)
                    );
            }
            if (containsPackageName(throwable.getMessage())) {
                return Problem
                    .builder()
                    .withTitle(status.getReasonPhrase())
                    .withStatus(status)
                    .withDetail(ERR_MSG)
                    .withCause(
                        Optional.ofNullable(throwable.getCause()).filter(cause -> isCausalChainsEnabled()).map(this::toProblem).orElse(null)
                    );
            }
        }

        return Problem
            .builder()
            .withTitle(status.getReasonPhrase())
            .withStatus(status)
            .withDetail(throwable.getMessage())
            .withCause(
                Optional.ofNullable(throwable.getCause()).filter(cause -> isCausalChainsEnabled()).map(this::toProblem).orElse(null)
            );
    }

    private boolean containsPackageName(String message) {
        // This list is for sure not complete
        return StringUtils.containsAny(message, "org.", "java.", "net.", "javax.", "com.", "io.", "de.", "com.yjy.climb");
    }
}
