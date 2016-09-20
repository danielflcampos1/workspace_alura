import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class OrdenaStrings {

	public static void main(String[] args) {

		List<String> palavras = new ArrayList<>();
		palavras.add("alura online");
		palavras.add("casa do código");
		palavras.add("caelum");

		// Collections.sort(palavras, comparable);
		palavras.sort((s1, s2) -> {
			if (s1.length() > s2.length())
				return -1;
			if (s1.length() < s2.length())
				return 1; 
			return 0;
		});

		palavras.sort((s1, s2) -> Integer.compare(s1.length(), s2.length()));
		palavras.sort(Comparator.comparing(s -> s.length()));
		palavras.sort(Comparator.comparing(String::length));
		
		Function<String , Integer> funcao = s -> s.length();
		palavras.sort(Comparator.comparing(funcao));

		System.out.println(palavras);
		
		palavras.forEach(s -> System.out.println(s));
		palavras.forEach(System.out::println);
		
		new Thread(() -> System.out.println("Executando um Runnable")).start();
	
	}
	
	
	
}