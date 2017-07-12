package de.leuphana.leuphanytics.model;

/*Object inside of Object Game*/

	import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

	@JsonIgnoreProperties(ignoreUnknown = true)
	public class InnerGame {

	    private Long id;
	    private String quote;

	    public InnerGame() {
	    }

	    public Long getId() {
	        return this.id;
	    }

	    public String getQuote() {
	        return this.quote;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public void setQuote(String quote) {
	        this.quote = quote;
	    }

	    @Override
	    public String toString() {
	        return "Value{" +
	                "id=" + id +
	                ", quote='" + quote + '\'' +
	                '}';
	    }
	
}
