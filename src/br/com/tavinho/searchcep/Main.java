package br.com.tavinho.searchcep;

import br.com.tavinho.searchcep.models.Address;
import br.com.tavinho.searchcep.utils.DataRetriever;
import br.com.tavinho.searchcep.utils.Finder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        showMenu();
        getUserInput();
    }

    private static void showMenu(){
        System.out.println(
                """
                SEARCHCEP
                ==============================
                Seja bem-vindo ao SearchCEP!
                Digite uma das opções abaixo para selecioná-las
                
                1 - Buscar endereço pelo CEP
                2 - Buscar CEP pelo endereço
                
                ==============================
                """);
    }

    private static void getUserInput(){
        Scanner scanner = new Scanner(System.in);

        int option = scanner.nextInt();
        scanner.nextLine();

        Address address = new Address();

        if (option == 1) {
            System.out.println("Insira o CEP (Apenas números): ");
            String cep = scanner.nextLine();
            address.setCEP(cep);
        } else {
            System.out.println("Insira a UF (SP, RJ, MG...): ");
            String uf = scanner.nextLine();
            System.out.println("Insira a cidade (São Paulo, Porto Alegre...): ");
            String city = scanner.nextLine();
            System.out.println("Insira o logradouro (EX: Praça da Sé): ");
            String avenue = scanner.nextLine();

            address.setUf(uf);
            address.setCity(city);
            address.setAvenue(avenue);
        }

        scanner.close();

        createNewJsonFile(address, option);
    }

    private static void createNewJsonFile(Address address, int option){
        Finder finder = new Finder();

        String data;

        if (option == 1) data = finder.getAddressBy(address.getCEP());
        else data = finder.getAddressBy(address.getUf(), address.getCity(), address.getAvenue());

        List<Address> addressList = DataRetriever.formatDataIntoList(data, option);

        try {
            FileWriter writer = new FileWriter("address.txt");
            for (Address ad: addressList) {
                writer.write(ad.toString());
            }
            writer.close();
        } catch (IOException e) {
            System.err.println("ERRO -> Não foi possível criar o arquivo.");
        }

    }

}