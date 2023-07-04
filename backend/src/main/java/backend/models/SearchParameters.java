package backend.models;

public class SearchParameters {
  private String character;
  private Integer page;

  public SearchParameters() {
  }

  public SearchParameters(String character, Integer page) {
    this.character = character;
    this.page = page;
  }

  public String getCharacter() {
    return character;
  }

  public void setCharacter(String character) {
    this.character = character;
  }

  public Integer getPage() {
    return page;
  }

  public void setPage(Integer page) {
    this.page = page;
  }

}
