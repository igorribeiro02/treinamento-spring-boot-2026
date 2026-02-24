package br.uff.sti;

import tools.jackson.databind.ObjectMapper;

import java.util.Map;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        ObjectMapper objectMapper = new ObjectMapper();

        Map map = objectMapper.readValue("""
        {
            "a":3,
            "b":4
        }
        """,
                Map.class);
        System.out.println( "Hello World! " + map );
    }
}
