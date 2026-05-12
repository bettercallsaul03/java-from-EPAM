public enum ShotResult {
    MISS("Мимо"),
    HIT("Попадание"),
    SUNK("Корабль потоплен"),
    WIN("Победа"),
    REPEAT("В эту клетку уже стреляли"),
    INVALID("Координаты вне поля");

    private final String message;

    ShotResult(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
