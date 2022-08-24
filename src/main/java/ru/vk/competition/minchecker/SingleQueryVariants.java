package ru.vk.competition.minchecker;

public enum SingleQueryVariants {
    ADD(201, 400, "add-new-query-result", "add-new-query", "single-query/"),
    DELETE(202, 406, "add-delete-result", "delete-single-query-by-id/", "single-query/"),
    MODIFY(200, 406, "add-modify-result", "modify-single-query", "single-query/"),
    EXECUTE(201, 406, "add-execute-result", "execute-single-query-by-id/", "single-query/"),
    GET(200, 500, "", "get-single-query-by-id/", "single-query/"),
    GETALL(200, 200, "", "get-all-single-queries", "single-query/");

    private int positiveCode;
    private int negativeCode;
    private String resultQuery;
    private String query;
    private String type;

    SingleQueryVariants(int positiveCode, int negativeCode, String resultQuery, String query, String type) {
        this.positiveCode = positiveCode;
        this.negativeCode = negativeCode;
        this.resultQuery = resultQuery;
        this.query = query;
        this.type = type;
    }

    public int getPositiveCode() {
        return positiveCode;
    }

    public int getNegativeCode() {
        return negativeCode;
    }

    public String getResultQuery() {
        return resultQuery;
    }

    public String getQuery() {
        return query;
    }

    public String getType() {
        return type;
    }
}
