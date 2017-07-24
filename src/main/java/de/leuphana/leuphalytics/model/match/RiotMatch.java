package de.leuphana.leuphalytics.model.match;

public class RiotMatch extends Match {

	private RegionEnum region;
	private String platformId;
	private int champion;
//	private QueueEnum queue;
	private int queue;
	private int season;
	private long timestamp;
	private LaneEnum lane;
	private MatchRoleEnum matchRole;
	
	
	public RegionEnum getRegion() {
		return region;
	}
	public void setRegion(RegionEnum region) {
		this.region = region;
	}
	public String getPlatformId() {
		return platformId;
	}
	public void setPlatformId(String platformId) {
		this.platformId = platformId;
	}
	public int getChampion() {
		return champion;
	}
	public void setChampion(int champion) {
		this.champion = champion;
	}
	public int getQueue() {
		return queue;
	}
	public void setQueue(int queue) {
		this.queue = queue;
	}
	public int getSeason() {
		return season;
	}
	public void setSeason(int season) {
		this.season = season;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public LaneEnum getLane() {
		return lane;
	}
	public void setLane(LaneEnum lane) {
		this.lane = lane;
	}
	public MatchRoleEnum getMatchRole() {
		return matchRole;
	}
	public void setMatchRole(MatchRoleEnum matchRole) {
		this.matchRole = matchRole;
	}
	
	
	

//	constructor test
	
//	public RiotMatch(int matchId, String region, String platformId, long championId, String queue, String season,
//			long timestamp, String lane, String matchRole) {
//
//		this.matchId = matchId;
//		this.region = RegionEnum.valueOf(region);
//		this.platformId = platformId;
//		this.championId = championId;
//		this.queue = QueueEnum.valueOf(queue);
//		this.season = season;
//		this.timestamp = timestamp;
//		this.lane = LaneEnum.valueOf(lane);
//		this.matchRole = MatchRoleEnum.valueOf(matchRole);
//	}

}
