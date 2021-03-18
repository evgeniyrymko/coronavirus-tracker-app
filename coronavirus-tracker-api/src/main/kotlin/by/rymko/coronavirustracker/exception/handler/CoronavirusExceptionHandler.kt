package by.rymko.coronavirustracker.exception.handler

import by.rymko.coronavirustracker.exception.CoronavirusDataException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class CoronavirusExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(value = [(CoronavirusDataException::class)])
    fun handleCoronavirusDataException(exception: CoronavirusDataException, request: WebRequest): ResponseEntity<String> {
        val exceptionDetails = "Can not obtain Coronavirus Data, $exception"
        return ResponseEntity(exceptionDetails, HttpStatus.BAD_REQUEST)
    }
}
