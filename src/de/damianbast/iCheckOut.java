package de.damianbast;

public interface iCheckOut {
    public void scan (String item) throws ScanException;
    public int total();
}
