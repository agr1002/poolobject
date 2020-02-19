/**
 * 
 */
package ubu.gii.dass.test.c01;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ubu.gii.dass.c01.Reusable;
import ubu.gii.dass.c01.ReusablePool;

/**
 * @author Antoni Lluis García Expósito - Alejandro Goicoechea Román
 *
 */
public class ReusablePoolTest {

	/** Pool contenedor de los objetos reusables. */
	private ReusablePool pool;

	/** Objeto reusable A. */
	private Reusable reusableA;

	/** Objeto reusable B. */
	private Reusable reusableB;

	/**
	 * Inicializamos una instancia para ser reusada a posteriori.
	 * 
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		pool = ReusablePool.getInstance();
		reusableA = pool.acquireReusable();
		reusableB = pool.acquireReusable();
	}

	/**
	 * Liberamos las instancias reusadas.
	 * 
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		pool.releaseReusable(reusableA);
		pool.releaseReusable(reusableB);
	}

	/**
	 * Test method for {@link ubu.gii.dass.c01.ReusablePool#getInstance()}.
	 */
	@Test
	public void testGetInstance() {
		ReusablePool instanciaPool = ReusablePool.getInstance();

		/*
		 * Comprobamos que la instancia global pool y la instancia local instanciaPool
		 * son de tipo ReusablePool.
		 */
		assertTrue(pool instanceof ReusablePool);
		assertTrue(instanciaPool instanceof ReusablePool);

		/*
		 * Comprobamos que ambas instancias tienen mismos valores y que, además, se
		 * tratan del mismo objeto (esto es, no son nuevas instancias, o sea, se cumple
		 * el patrón Singleton).
		 */
		assertTrue((pool == instanciaPool) && (pool.equals(instanciaPool)));
	}
	
	/**
	 * Test method for {@link ubu.gii.dass.c01.ReusablePool#acquireReusable()}.
	 */
	@Test
	public void testAcquireReusable() {
		//Comentario vacio
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link ubu.gii.dass.c01.ReusablePool#releaseReusable(ubu.gii.dass.c01.Reusable)}.
	 */
	@Test
	public void testReleaseReusable() {
		fail("Not yet implemented");
	}

}
