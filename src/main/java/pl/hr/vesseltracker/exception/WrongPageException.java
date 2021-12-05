package pl.hr.vesseltracker.exception;

public class WrongPageException extends RuntimeException
{
    public WrongPageException(final String message)
    {
        super(message);
    }

}