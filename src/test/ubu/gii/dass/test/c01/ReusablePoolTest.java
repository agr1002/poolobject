/**
 * 
 */
package ubu.gii.dass.test.c01;

import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ubu.gii.dass.c01.DuplicatedInstanceException;
import ubu.gii.dass.c01.NotFreeInstanceException;
import ubu.gii.dass.c01.Reusable;
import ubu.gii.dass.c01.ReusablePool;

/**
 * @author Antoni Lluis García Expósito - Alejandro Goicoechea Román
 *
 */
public class ReusablePoolTest {

	/** Pool contenedor de los objetos reusables. */
	private ReusablePool pool;

	
	/** Objeto reusable B. */
	private Vector<Reusable> vector;
	

	/**
	 * Inicializamos una instancia para ser reusada a posteriori.
	 * 
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		pool = ReusablePool.getInstance();
		this.vector = new Vector<Reusable>();
		
	}

	/**
	 * Liberamos las instancias reusadas.
	 * 
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		for(Reusable reusable: vector) {
			pool.releaseReusable(reusable);
		}

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
		assertTrue("No se cumple el patrón Singleton porque hay más de una instancia", (pool == instanciaPool) && (pool.equals(instanciaPool)));
	}
	
	/**
	 * Test method for {@link ubu.gii.dass.c01.ReusablePool#acquireReusable()}.
	 */
	@Test
	public void testAcquireReusable() throws Exception {
		try {
			for(int i = 0; i < 3; i++) {
				vector.add(pool.acquireReusable());
			}
		} catch (NotFreeInstanceException ex) {
			System.out.println("No se ha podido adquirir una instancia del objeto Reusable disponible en el pool.");
		}
	}
		
	

	/**
	 * Test method for
	 * {@link ubu.gii.dass.c01.ReusablePool#releaseReusable(ubu.gii.dass.c01.Reusable)}.
	 * @throws NotFreeInstanceException 
	 */
	@Test
	public void testReleaseReusable() throws NotFreeInstanceException {
		Reusable x1 = pool.acquireReusable();
		Reusable x2 = pool.acquireReusable();
		
		try {
			pool.releaseReusable(x1);
		
		}catch(DuplicatedInstanceException e) {
			System.err.println("No se ha devuelto el resultado.");
			assertTrue(false);
		}
		try {
			pool.releaseReusable(x2);
		}catch(DuplicatedInstanceException e) {
			System.err.println("No se ha devuelto el resultado.");
			assertTrue(false);
		}
		
		try {
			pool.releaseReusable(x1);
			assertTrue(false);
		}catch(DuplicatedInstanceException e) {
			assertTrue(true);
		}
		
		try {
			pool.releaseReusable(x2);
			assertTrue(false);
		}catch(DuplicatedInstanceException e) {
			assertTrue(true);
		}
		
		
	}

}
