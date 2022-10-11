package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Stack;

public class Main {

    public static void main(String[] args){

        StringBuilder sb = new StringBuilder();
        // caminho do artigo
        Path path = Paths.get("Calc1.stk");

        String line;
        Stack stack = new Stack();
        int firstNum;
        int secndNum;
        ArrayList<Token> tokenList = new ArrayList<>();
        Boolean error = false;

        try (BufferedReader br = Files.newBufferedReader(path)) {

            // lendo linha por linha do arquivo
            while ((line = br.readLine()) != null) {
                if (Regex.isNum(line)){
                    // é número
                    TokenType token = TokenType.NUM;
                    Token tokenNum = new Token( token, line);
                    tokenList.add(tokenNum); // adiciona o token do numero na lista de tokens
                }
                else if (Regex.isOP(line)){
                    // é operando

                    // verifica qual o operando para adicionar na lista de token
                    if (Regex.isStar(line)){
                        TokenType token = TokenType.STAR;
                        Token tokenOp = new Token( token, line);
                        tokenList.add(tokenOp);
                    }
                    else if (Regex.isPlus(line)){
                        TokenType token = TokenType.PLUS;
                        Token tokenOp = new Token( token, line);
                        tokenList.add(tokenOp);
                    }
                    else if (Regex.isMinus(line)){
                        TokenType token = TokenType.MINUS;
                        Token tokenOp = new Token( token, line);
                        tokenList.add(tokenOp);
                    }
                    else if (Regex.isSlash(line)){
                        TokenType token = TokenType.SLASH;
                        Token tokenOp = new Token( token, line);
                        tokenList.add(tokenOp);
                    }
                }
                else {
                    // se não é nem número nem operando, printa mensagem de erro
                    error = true;
                    System.out.println("Error: unexpected character: " + line);

                }
            }

        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }

        // se ocorreu erro, no caso, leu um caractere inesperado, nao precisa ler a tokenList para realizar as operaçoes na pilha
        if (!error) {
            for (Token token : tokenList){
                if (token.type.equals(TokenType.NUM)){
                    // se for numero, apenas adiciona na pilha
                    stack.push(token.lexeme);
                }
                else {
                    // se não for número é operando, verifica qual é para fazer a operaçao
                    secndNum = Integer.parseInt((String) stack.pop()); // segundo operando
                    firstNum = Integer.parseInt((String) stack.pop()); // primeiro operando

                    // realizará a conta entre secndNum e firstNum de acordo com o sinal que leu
                    if (Regex.isStar(token.lexeme)){
                        stack.push((firstNum*secndNum)+"");
                    }
                    else if (Regex.isPlus(token.lexeme)){
                        stack.push((firstNum+secndNum)+"");
                    }
                    else if (Regex.isMinus(token.lexeme)){
                        stack.push((firstNum-secndNum)+"");
                    }
                    else if (Regex.isSlash(token.lexeme)){
                        stack.push((firstNum/secndNum)+"");
                    }
                }
                System.out.println(token);
            }


            // ao fim de tudo, o único elemento na pilha será o resultado final
            System.out.println(stack.pop());
        }
    }
}