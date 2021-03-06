package ie.logn.dao.springjdbc;

import ie.logn.dao.ResultDao;
import ie.logn.data.Result;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;

public class ResultJdbcDao extends SpringJdbcDao<Result> implements ResultDao {
	
	public ResultJdbcDao(final DataSource dataSource,  final DataFieldMaxValueIncrementer incrementer)
	{
		super(dataSource, incrementer);
		this.insert.withTableName("RESULT").setGeneratedKeyName("ID");
	}

	@Override
	public void saveResult(Result result) {
		super.saveIdentityObject(result);
	}

	@Override
	public Result getResult(long id) {
		Map<String, Long> namedParameters = Collections.singletonMap("ID", id);
		
		try {
			return jdbcTemplate.queryForObject(SQL_GET_RESULT, namedParameters, new ResultRowMapper());
		} catch (EmptyResultDataAccessException ex)
		{
			return null;
		}
	}
	
	@Override
	public void deleteResult(long resultId) {
		super.deleteObject(resultId);
		
	}

	@Override
	public void modifyResult(Result result) {
		// TODO Auto-generated method stub
	}
	
	
	@Override
	public Result getResultForFixture(long fixtureId) {
		Map<String, Long> sqlParameters = Collections.singletonMap("FIXTURE", fixtureId);
		
		return jdbcTemplate.queryForObject(SQL_GET_RESULT_FOR_FIXTURE, sqlParameters, new ResultRowMapper());
	}
	
	private class ResultRowMapper implements RowMapper<Result>
	{

		@Override
		public Result mapRow(ResultSet rs, int arg1) throws SQLException {
			return new Result(
					rs.getLong("ID"),
					rs.getLong("FIXTURE"),
					rs.getString("HOME_SCORE"),
					rs.getString("AWAY_SCORE") );
		}
	}
	
	private static final String SQL_GET_RESULT = "select ID, FIXTURE, HOME_SCORE, AWAY_SCORE from RESULT where ID = :ID";

	private static final String SQL_GET_RESULT_FOR_FIXTURE = "select ID, FIXTURE, HOME_SCORE, AWAY_SCORE from RESULT where FIXTURE = :FIXTURE";
}
