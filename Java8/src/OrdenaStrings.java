import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class OrdenaStrings {
	
	public static void main(String[] args) {
		List<String> palavras = new ArrayList<String>();
		
		palavras.add("Daniel Campos");
		palavras.add("Vanessa Soares");
		palavras.add("Lucia Costa");
		
		
		Comparator<String> comparable = new ComparaPalavras();
		Collections.sort(palavras, comparable);
		System.out.println(palavras);
		
	}
}

class ComparaPalavras implements Comparator<String>{
	@Override
	public int compare(String s1, String s2) {
		if(s1.length() > s2.length())
			return -1;
		if(s1.length() < s2.length())
			return 1;
		return 0;
	}
}