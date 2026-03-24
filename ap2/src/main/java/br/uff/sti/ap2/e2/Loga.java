package br.uff.sti.ap2.e2;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// Anotação para marcar os métodos que devem ser logados
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME) // A anotação estará disponível em tempo de execução para o aspecto interceptar os métodos anotados
public @interface Loga { // Anotação para marcar os métodos que devem ser logados
}