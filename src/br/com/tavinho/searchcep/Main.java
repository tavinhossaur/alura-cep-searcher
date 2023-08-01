package br.com.tavinho.searchcep;

import br.com.tavinho.searchcep.models.Address;
import br.com.tavinho.searchcep.utils.DataRetriever;
import br.com.tavinho.searchcep.utils.AddressFinder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * The Main class for the SearchCEP application.
 * This application allows the user to search for an address by CEP (postal code)
 * or search for a CEP by providing address information.
 */
public class Main {

    /**
     * The entry point of the SearchCEP application.
     * Shows the main menu and gets user input to determine the operation to perform.
     *
     * @param args The command-line arguments (not used).
     */
    public static void main(String[] args) {
        showMenu();
        getUserInput();
    }

    /**
     * Displays the main menu of the application.
     * The user is prompted to choose between searching for an address by CEP
     * or searching for a CEP by providing address information.
     */
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
                """
        );
    }

    /**
     * Gets user input based on the chosen option from the main menu.
     * Depending on the option, the user will be prompted to enter either a CEP or address information.
     * The entered data is used to create an {@link Address} object.
     * After creating the object, it calls {@link #createNewJsonFile(Address, boolean)} to fetch the data
     * from the API, deserialize it, and create a new JSON file with the results.
     */
    private static void getUserInput(){
        Scanner scanner = new Scanner(System.in);
        Address address;

        boolean isCEP = checkValidOption(scanner);

        if (isCEP) {
            System.out.println("Insira o CEP (Apenas números): ");
            String cep = scanner.nextLine();

            address = new Address(cep);
        } else {
            System.out.println("Insira a UF (EX: SP): ");
            String uf = scanner.nextLine();
            System.out.println("Insira a cidade (EX: São Paulo): ");
            String city = scanner.nextLine();
            System.out.println("Insira o logradouro (EX: Praça da Sé): ");
            String avenue = scanner.nextLine();

            address = new Address(uf, city, avenue);
        }

        scanner.close();

        createNewJsonFile(address, isCEP);
    }

    /**
     * Creates a new JSON file based on the fetched data from the API.
     * The {@link AddressFinder} class is used to fetch data based on the chosen option.
     * The fetched data is then deserialized into a list of {@link Address} objects using {@link DataRetriever}.
     * Finally, the deserialized data is written to a new JSON file named "address.txt".
     *
     * @param address The {@link Address} object containing CEP or address information.
     * @param isCEP  The user-selected option (true for address by CEP, false for CEP by address).
     */
    private static void createNewJsonFile(Address address, boolean isCEP){
        AddressFinder finder = new AddressFinder();

        String data;

        if (isCEP) data = finder.getAddressBy(address.getCEP());
        else data = finder.getAddressBy(address.getUf(), address.getCity(), address.getAvenue());

        List<Address> addressList = DataRetriever.deserializeData(data, isCEP);

        Collections.sort(addressList);

        try {
            FileWriter writer = new FileWriter("address.txt");
            for (Address ad: addressList) {
                writer.write(ad.toString());
            }
            writer.close();
        } catch (IOException e) {
            System.err.println("ERRO: Não foi possível criar o arquivo.");
        }
    }

    /**
     * Checks for a valid option selected by the user.
     * The user has to input 1 or 2 to select a valid option and
     * then his choice is validated to check if he wants to search for an address by CEP
     * or vice-versa.
     *
     * @param scanner An already created instance of the Scanner class.
     * @return A boolean value that represents the choice.
     */
    private static boolean checkValidOption(Scanner scanner) {
        int option;

        do {
            System.out.print("Digite a opção desejada (1 ou 2): ");
            while (!scanner.hasNextInt()) {
                System.out.println("Opção inválida. Digite 1 ou 2.");
                scanner.next(); // Discard invalid input
            }
            option = scanner.nextInt();
        } while (option != 1 && option != 2);

        scanner.nextLine();

        return option == 1;
    }
}
