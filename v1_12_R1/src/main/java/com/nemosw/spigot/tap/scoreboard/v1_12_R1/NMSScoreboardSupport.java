package com.nemosw.spigot.tap.scoreboard.v1_12_R1;

import com.nemosw.spigot.tap.scoreboard.ScoreboardSupport;

public final class NMSScoreboardSupport extends ScoreboardSupport
{
	@Override
	public NMSScoreboard newScoreboard()
	{
		return new NMSScoreboard();
	}

}
