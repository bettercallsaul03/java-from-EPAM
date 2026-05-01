public enum ComfortLevel {
    ОБЩИЙ(1, "Общий"),
    ПЛАЦКАРТ(2, "Плацкарт"),
    КУПЕ(3, "Купе"),
    СВ(4, "СВ"),
    ЛЮКС(5, "Люкс");

    private final int rank;
    private final String title;

    ComfortLevel(int rank, String title) {
        this.rank = rank;
        this.title = title;
    }

    public int getRank() {
        return rank;
    }

    public String getTitle() {
        return title;
    }
}
