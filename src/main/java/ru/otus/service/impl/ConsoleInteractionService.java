package ru.otus.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.service.InteractionService;

import java.util.Scanner;

@Service
public class ConsoleInteractionService implements InteractionService {

    @Override
    public void write(String data) {
        System.out.println(data);
    }

    @Override
    public String read() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

}
