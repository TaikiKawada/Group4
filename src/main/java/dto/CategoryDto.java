package dto;

public class CategoryDto {
    private int id;
    private String name;

    // デフォルトコンストラクタ
    public CategoryDto() {}

    // コンストラクタ（全フィールド）
    public CategoryDto(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // getter / setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
