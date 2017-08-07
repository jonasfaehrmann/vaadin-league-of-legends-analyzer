package de.leuphana.backend.data;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import de.leuphana.backend.data.entity.Product;
import net.rithms.riot.api.endpoints.match.dto.MatchReference;
import net.rithms.riot.api.endpoints.static_data.dto.Champion;
import net.rithms.riot.api.endpoints.static_data.dto.Item;

public class DashboardData {
	
	private Map<String, Champion> dataChampionList;
	private List<Item> itemList;
	private List<MatchReference> matchListForUser;
	private DeliveryStats deliveryStats;
	private List<Number> deliveriesThisMonth;
	private List<Number> deliveriesThisYear;
	private Object salesLastYears;
	private Number[][] salesPerMonth;
	private LinkedHashMap<Product, Integer> productDeliveries;

	public List<MatchReference> getMatchListForUser() {
		return matchListForUser;
	}

	public void setMatchListForUser(List<MatchReference> matchListForUser) {
		this.matchListForUser = matchListForUser;
	}
	
	public DeliveryStats getDeliveryStats() {
		return deliveryStats;
	}

	public void setDeliveryStats(DeliveryStats deliveryStats) {
		this.deliveryStats = deliveryStats;
	}

	public List<Number> getDeliveriesThisMonth() {
		return deliveriesThisMonth;
	}

	public void setDeliveriesThisMonth(List<Number> deliveriesThisMonth) {
		this.deliveriesThisMonth = deliveriesThisMonth;
	}

	public List<Number> getDeliveriesThisYear() {
		return deliveriesThisYear;
	}

	public void setDeliveriesThisYear(List<Number> deliveriesThisYear) {
		this.deliveriesThisYear = deliveriesThisYear;
	}

	public Object getSalesLastYears() {
		return salesLastYears;
	}

	public void setSalesLastYears(Object salesLastYears) {
		this.salesLastYears = salesLastYears;
	}

	public Number[][] getSalesPerMonth() {
		return salesPerMonth;
	}

	public void setSalesPerMonth(Number[][] salesPerMonth) {
		this.salesPerMonth = salesPerMonth;
	}

	public Number[] getSalesPerMonth(int i) {
		return salesPerMonth[i];
	}

	public LinkedHashMap<Product, Integer> getProductDeliveries() {
		return productDeliveries;
	}

	public void setProductDeliveries(LinkedHashMap<Product, Integer> productDeliveries) {
		this.productDeliveries = productDeliveries;
	}

	public List<Item> getItemList() {
		return itemList;
	}

	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}

	public Map<String, Champion> getDataChampionList() {
		return dataChampionList;
	}

	public void setDataChampionList(Map<String, Champion> dataChampionList) {
		this.dataChampionList = dataChampionList;
	}

}
