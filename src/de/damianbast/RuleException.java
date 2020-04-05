package de.damianbast;

public class RuleException extends Throwable{
    public RuleException(String error_string)
    {
        System.out.println(error_string);
    }
}
