package com.mapicallo.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal para iniciar la aplicación Spring Boot.
 *
 * Usa la anotación @SpringBootApplication, que es equivalente a:
 * - @Configuration: Indica que esta clase puede contener beans de configuración.
 * - @EnableAutoConfiguration: Activa la configuración automática de Spring Boot.
 * - @ComponentScan: Escanea los paquetes en busca de componentes, servicios y controladores.
 */
@SpringBootApplication
public class RestapiProductApplication {

	/**
	 * Método principal para arrancar la aplicación.
	 *
	 * @param args Argumentos de línea de comandos.
	 */
	public static void main(String[] args) {
		// Arranca la aplicación usando SpringApplication
		SpringApplication.run(RestapiProductApplication.class, args);
	}
}
