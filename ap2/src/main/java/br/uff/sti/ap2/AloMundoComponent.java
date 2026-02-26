package br.uff.sti.ap2;

import org.springframework.stereotype.Component;

@Component
public class AloMundoComponent {
    public void aloMundo(String mundo){
        System.out.println(".");
        System.out.println(".");
        System.out.println("Alô mundo " + mundo);
        System.out.println(".");
        System.out.println(".");
    }
}
