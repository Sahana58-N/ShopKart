package com.ecommerce.Shopkart.Utility;

public class Util
{
    public static boolean stringChecker(String str)
    {
        if(str!=null && !str.isEmpty() && !str.equalsIgnoreCase("null") && !str.equalsIgnoreCase("na") )
        {
            return true;
        }else {
            return false;
        }
    }
}
