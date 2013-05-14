package phonetic_transcriber_tests;

import static org.junit.Assert.*;

import org.junit.Test;

import phonetic_character_converter.AlphaNumericCharacterConverter;
import phonetic_character_converter.AlphabeticCharacterConverter;
import phonetic_transcriber.PhoneticTranscriber;

public class PhoneticTranscriberTests {
	
	private PhoneticTranscriber _phoneticTranscriber;



	
	@Test
	public void testTranscribe() {
		this._phoneticTranscriber=new PhoneticTranscriber("_", null);
		String word="apli";
		String result="a_p_l_ix";
		assertEquals(result, this._phoneticTranscriber.transcribe(word));
	}
	

	@Test
	public void testExceptionTranscribe() {
		this._phoneticTranscriber=new PhoneticTranscriber("_", null);
		String word="apģērbti";
		String result="a_p_G_EE_r_p_t_ix";
		assertEquals(result, this._phoneticTranscriber.transcribe(word));
	}
	
	@Test
	public void ēķī() {
		this._phoneticTranscriber=new PhoneticTranscriber("_", null);
		String word="ēķī";
		String result="ee_K_ii";
		assertEquals(result, this._phoneticTranscriber.transcribe(word));
	}
	
	@Test
	public void ē() {
		this._phoneticTranscriber=new PhoneticTranscriber("_", null);
		String word="ē";
		String result="EE";
		assertEquals(result, this._phoneticTranscriber.transcribe(word));
	}
	
	@Test
	public void puškins() {
		this._phoneticTranscriber=new PhoneticTranscriber("_", null);
		String word="puškins";
		String result="p_u_S_k_i_n_s";
		assertEquals(result, this._phoneticTranscriber.transcribe(word));
	}
	
	@Test
	public void AlphaNumeric() {
		this._phoneticTranscriber=new PhoneticTranscriber(" ", new AlphaNumericCharacterConverter());
		String word="nospiedošs";
		String result="n u035Co s p i035Ce d 0254 0283";
		assertEquals(result, this._phoneticTranscriber.transcribe(word));
	}

}
