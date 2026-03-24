package br.uff.sti.ap2.e2;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Aspect
@Component
public class LogaAspect {

    // Intercepta qualquer método que tenha a anotação @Loga
    @Before("@annotation(Loga)")
    public void logarExecucao(JoinPoint joinPoint) {
        String nomeMetodo = joinPoint.getSignature().getName();
        String nomeClasse = joinPoint.getTarget().getClass().getSimpleName();
        String dataHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));

        // O "true" no FileWriter faz com que ele adicione as linhas sem apagar o que já existe (append).
        // Uso de try-with-resources conforme exigência para garantir que os recursos sejam fechados corretamente.
        try (FileWriter fw = new FileWriter("log_auditoria.csv", true);
             PrintWriter pw = new PrintWriter(fw)) {

            pw.println(nomeClasse + "," + nomeMetodo + "," + dataHora);

        } catch (Exception e) {
            System.err.println("Erro ao gravar o log: " + e.getMessage());
        }
    }
}