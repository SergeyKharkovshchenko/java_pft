package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Sergey on 18.04.2016.
 */

public class PointTests {
  @Test
  public void testDistance (){
    Point point1 = new Point (1,1);
    Point point2 = new Point (1,2);
    Assert.assertEquals(point1.distance(point2),1.0);

    Point point3 = new Point (1,1);
    Point point4 = new Point (5,1);
    Assert.assertEquals(point3.distance(point4),4.0);
  }
}
