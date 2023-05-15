package sample;

public class Exceptions {

	public void validateGender(String gender) throws UnknownGenderException {
		if ( !((gender.equals("female")) || (gender.equals("Female")) || (gender.equals("male")) || (gender.equals("female"))) ){
			throw new UnknownGenderException("GenderNotDefined");
		}
	}
	
	public void validateId(int id) throws NegativeIdException {
		if(id<0) {
			throw new NegativeIdException("NegativeId");
		}
	}
	
	public void validateAge(int age) throws ZeroAgeException {
		if(age<=0) {
			throw new ZeroAgeException("IncorrectAge");
		}
	}
	
	public void validateDays(int days) throws UnknownAdmissionDaysException {
		if(days<=0) {
			throw new UnknownAdmissionDaysException("IncorrectDays");
		}
	}
	
}
