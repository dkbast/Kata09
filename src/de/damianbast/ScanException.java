package de.damianbast;

public class ScanException extends Throwable{
    public ScanException(String error_string)
    {
        System.out.println(error_string);
    }
}
