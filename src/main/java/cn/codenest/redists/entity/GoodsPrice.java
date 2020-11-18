package cn.codenest.redists.entity;

import java.util.Date;

public class GoodsPrice {

    private Long id;

    private String board;

    private Date publishDate;

    private String name;

    private String unit;

    private Double price;

    private Double increaseofday;

    private Double increaseofyear;

    private Date recordTime;

    public GoodsPrice(Long id, String board, Date publishDate, String name, String unit, Double price, Double increaseofday, Double increaseofyear, Date recordTime) {
        this.id = id;
        this.board = board;
        this.publishDate = publishDate;
        this.name = name;
        this.unit = unit;
        this.price = price;
        this.increaseofday = increaseofday;
        this.increaseofyear = increaseofyear;
        this.recordTime = recordTime;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GoodsPrice() {
        super();
    }

    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board == null ? null : board.trim();
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getIncreaseofday() {
        return increaseofday;
    }

    public void setIncreaseofday(Double increaseofday) {
        this.increaseofday = increaseofday;
    }

    public Double getIncreaseofyear() {
        return increaseofyear;
    }

    public void setIncreaseofyear(Double increaseofyear) {
        this.increaseofyear = increaseofyear;
    }

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }
}