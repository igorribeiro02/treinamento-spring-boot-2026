package br.uff.sti;

import lombok.val;

public class AppLombok {

    public static void main(String[] args){
        val pessoa = new Pessoa("a", "146594");

        val pessoa2 = pessoa.withCpf("cpf");

        if("cpf".equals(pessoa2.cpf())){
            System.out.println("A pessoa tem cpf = 'cpf': " + pessoa2);
        }else{
            System.out.println("A pessoa não tem cpf = 'cpf': " + pessoa2);
        }
    }
}
