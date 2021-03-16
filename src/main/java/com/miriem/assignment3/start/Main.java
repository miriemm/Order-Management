package com.miriem.assignment3.start;

        import com.miriem.assignment3.presentation.Parser;

        import java.sql.SQLException;
        import java.util.logging.Logger;

public class Main {

    protected static final Logger LOGGER = Logger.getLogger(Main.class.getCanonicalName());


    public static void main(String[] args) throws SQLException{
        Parser parser = new Parser();
        parser.parse();

    }

}
