package rent.server.persistencia;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.google.inject.ScopeAnnotation;

/**
 * Es una anotacion que demarca como "transaccional" un método. Es necesaria
 * para poder persistir en la base de datos. (SOLO PERSISTIR).
 */
@Target(METHOD)
@Retention(RUNTIME)
@ScopeAnnotation
public @interface Transaccional {/**/}
