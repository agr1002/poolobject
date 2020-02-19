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

	/** Vector de objetos de tipo Reusable. */
	private Vector<Reusable> vector;
	
	/**
	 * Inicializamos una instancia para ser reusada a posteriori.
	 * 
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		pool = ReusablePool.getInstance();
		vector = new Vector<Reusable>();
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
		assertTrue("No se cumple el patrón Singleton ya que hay más de una instancia.", (pool == instanciaPool) && (pool.equals(instanciaPool)));
	}

	/**
	 * Test method for {@link ubu.gii.dass.c01.ReusablePool#acquireReusable()}.
	 * @throws Exception 
	 */
	@Test
	public void testAcquireReusable() {
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
	 * @throws NotFreeInstanceException, DuplicatedInstanceException 
	 */
	@Test
	public void testReleaseReusable() throws NotFreeInstanceException, DuplicatedInstanceException {
		Reusable reusable1 = pool.acquireReusable();
		Reusable reusable2 = pool.acquireReusable();
		pool.releaseReusable(reusable1);
		pool.releaseReusable(reusable2);
		reusable1 = pool.acquireReusable();
		reusable2 = pool.acquireReusable();
		pool.releaseReusable(reusable1);
		pool.releaseReusable(reusable2);
	}
}
