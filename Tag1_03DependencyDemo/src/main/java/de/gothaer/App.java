package de.gothaer;

import de.gothaer.dependency.Dependency;

import de.gothaer.dependency.service.MyServiceUsingDependency;
import math.Multiplizierer;
import math.MultipliziererImpl;
import math.MultipliziererOptimierer;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        // 1000
        Multiplizierer multiplizierer = new MultipliziererImpl();

        //2000
        multiplizierer = new MultipliziererOptimierer(multiplizierer);

        System.out.println(multiplizierer.mult(1000,1));
    }
}
