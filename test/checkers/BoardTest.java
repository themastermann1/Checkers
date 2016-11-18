/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package checkers;

import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author sdm29
 */
public class BoardTest {
    
    public BoardTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of Board method, of class Board.
     */
    @Test
    public void testBoard() {
        System.out.println("Board");
        Board instance = new Board();
        //int i[] = instance.getEven();
        //System.out.println(i[3]);
        
        int x[][] = instance.getValidPos();
        instance.initValidPos(x);
        
       
        //x[31][0]=1;
        for(int p = 0; p<32; p++){
            System.out.println(x[p][0]);
            System.out.println(x[p][1]);
            System.out.println(p);
            System.out.println("\n");
        }
        
        /*
        int i = 0;
        int j = i% 2;
        System.out.println(j);
        * //proof that 0 is an even number!
        */
        assertEquals(instance.validPos[0][0], 1);   //x pos
        assertEquals(instance.validPos[0][1], 0);   //y pos
    }

    /**
     * Test of initBlack method, of class Board.
     *
    @Test
    public void testInitBlack() {
        System.out.println("initBlack");
        Board instance = new Board();
        instance.initBlack();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    * */
}
