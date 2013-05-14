package phonetic_character_converter;

public interface PhoneticCharacterConverter {
	String toIPAchar(String character);
	String formIPAchar(String character);
	String[] tokenize(String word);
}
