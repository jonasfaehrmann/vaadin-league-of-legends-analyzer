package de.leuphana.leuphalytics.model.match;

public class RiotMatch extends Match {

	private RegionEnum region;
	private String platformId;
	private long championId;
	private QueueEnum queue;
	private String season;
	private long timestamp;
	private LaneEnum lane;
	private MatchRoleEnum matchRole;

	public RiotMatch(int matchId, String region, String platformId, long championId, String queue, String season,
			long timestamp, String lane, String matchRole) {

		this.matchId = matchId;
		this.region = RegionEnum.valueOf(region);
		this.platformId = platformId;
		this.championId = championId;
		this.queue = QueueEnum.valueOf(queue);
		this.season = season;
		this.timestamp = timestamp;
		this.lane = LaneEnum.valueOf(lane);
		this.matchRole = MatchRoleEnum.valueOf(matchRole);
	}

}
