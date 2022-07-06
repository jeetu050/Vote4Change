/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evoting.dto;

/**
 *
 * @author adity
 */
public class ElectionResult {

    public ElectionResult(String party, String symbol) {
        this.party = party;
        this.symbol = symbol;
    }

    public ElectionResult() {
    }

    @Override
    public String toString() {
        return "ElectionResult{" + "party=" + party + ", symbol=" + symbol + '}';
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
    private String party;
    private String symbol;
}
