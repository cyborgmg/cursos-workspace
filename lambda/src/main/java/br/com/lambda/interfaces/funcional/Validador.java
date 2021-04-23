package br.com.lambda.interfaces.funcional;

@FunctionalInterface
public interface Validador<T> {
	
	boolean valida(T t);
	
}