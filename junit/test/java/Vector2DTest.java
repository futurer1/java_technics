import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Vector2DTest {
    private final double EPS = 1e-9;
    private final double LOW_EPS = 1e-2;

    private Vector2D vector;

    @Before
    public void createNewVector() {
        vector = new Vector2D();
    }

    @Test
    public void newVectorShouldHaveZeroLength() {
        Assert.assertEquals(0, vector.length(), EPS);
    }

    @Test
    public void newVectorShouldHaveZeroX() {
        Assert.assertEquals(0, vector.getX(), EPS);
    }

    @Test
    public void newVectorShouldHaveZeroY() {
        Assert.assertEquals(0, vector.getY(), EPS);
    }

    @Test
    public void vector100() {
        vector.setX(10);
        vector.setY(4);
        //System.out.println(vector.length());
        Assert.assertEquals(10.77, vector.length(), LOW_EPS);
    }
}
