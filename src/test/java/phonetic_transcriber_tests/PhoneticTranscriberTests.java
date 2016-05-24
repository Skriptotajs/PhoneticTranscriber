package phonetic_transcriber_tests;

import static org.junit.Assert.*;

import org.junit.Test;

import phonetic_character_converter.AlphaNumericCharacterConverter;
import phonetic_transcriber.PhoneticTranscriber;

public class PhoneticTranscriberTests {
	
	private PhoneticTranscriber _phoneticTranscriber;

	@Test
	public void testTranscribe() throws Exception{
		this._phoneticTranscriber=new PhoneticTranscriber("_", null);
		String word="apli";
		String result="a_p_l_ix";
		assertEquals(result, this._phoneticTranscriber.transcribe(word));
	}

	@Test
	public void testExceptionTranscribe() throws Exception {
		this._phoneticTranscriber=new PhoneticTranscriber("_", null);
		String word="apģērbti";
		String result="a_p_G_EE_r_p_t_ix";
		assertEquals(result, this._phoneticTranscriber.transcribe(word));
	}
	
	@Test
	public void ēķī()  throws Exception {
		this._phoneticTranscriber=new PhoneticTranscriber("_", null);
		String word="ēķī";
		String result="ee_K_ii";
		assertEquals(result, this._phoneticTranscriber.transcribe(word));
	}
	
	@Test
	public void ē()  throws Exception {
		this._phoneticTranscriber=new PhoneticTranscriber("_", null);
		String word="ē";
		String result="EE";
		assertEquals(result, this._phoneticTranscriber.transcribe(word));
	}
	
	@Test
	public void puškins() throws Exception {
		this._phoneticTranscriber=new PhoneticTranscriber("_", null);
		String word="puškins";
		String result="p_u_S_k_i_n_s";
		assertEquals(result, this._phoneticTranscriber.transcribe(word));
	}
	
	@Test
	public void AlphaNumeric()  throws Exception{
		this._phoneticTranscriber=new PhoneticTranscriber(" ", new AlphaNumericCharacterConverter());
		String word="nospiedošs";
		String result="n u035Co s p i035Ce d 0254 0283";
		assertEquals(result, this._phoneticTranscriber.transcribe(word));
	}

}
