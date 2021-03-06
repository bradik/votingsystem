package com.example.votingsystem.web.controler;

import com.example.votingsystem.TestData;
import com.example.votingsystem.model.Restaurant;
import com.example.votingsystem.util.DateTimeUtil;
import com.example.votingsystem.web.controler.accessory.AbstractControllerTest;
import com.example.votingsystem.web.json.JsonUtil;
import com.example.votingsystem.web.to.VoteResultTo;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static com.example.votingsystem.TestData.*;
import static com.example.votingsystem.TestUtil.userHttpBasic;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Brad on 14.10.2017.
 */

public class VoteRestControlerTest extends AbstractControllerTest {

    private static final String REST_URL = VoteRestControler.REST_URL;//rest/user/votes

    private static final Restaurant TEST_BAR_1 = TestData.TEST_BAR_6;
    private static final Restaurant TEST_BAR_2 = TestData.TEST_BAR_7;

    @Before
    public void beforeTest() {

        restaurantService.save(TEST_BAR_1);
        restaurantService.save(TEST_BAR_2);

    }

    @Test
    public void getResultsByDateTest() throws Exception {

        voteService.putVoteOnDate(TEST_BAR_2.getId(), ADMIN.getId(), LocalDateTime.of(YESTERDAY, LocalTime.MIN));
        voteService.putVoteOnDate(TEST_BAR_2.getId(), USER.getId(), LocalDateTime.of(YESTERDAY, LocalTime.MIN));

        String actual =
        mockMvc.perform(get(REST_URL + "/results/by")
                .param("date", DateTimeUtil.of(YESTERDAY))
                .with(userHttpBasic(USER))
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        List<VoteResultTo> results = JsonUtil.readValues(actual, VoteResultTo.class);

        Long votes = 0L;
        for (VoteResultTo result : results) {
            if(TEST_BAR_2.getName().equals(result.getBarName())){
                votes = result.getVotes();
            }
        }

        assertThat(2L, is(votes));
    }

    @Test
    public void getResultsTest() throws Exception {

        voteService.putVote(TEST_BAR_1.getId(), ADMIN.getId());
        voteService.putVote(TEST_BAR_1.getId(), USER.getId());

        String actual =
                mockMvc.perform(get(REST_URL + "/results")
                        .param("date", DateTimeUtil.of(YESTERDAY))
                        .with(userHttpBasic(USER))
                )
                        .andDo(print())
                        .andExpect(status().isOk())
                        .andReturn().getResponse().getContentAsString();

        List<VoteResultTo> results = JsonUtil.readValues(actual, VoteResultTo.class);

        Long votes = 0L;
        for (VoteResultTo result : results) {
            if(TEST_BAR_1.getName().equals(result.getBarName())){
                votes = result.getVotes();
            }
        }

        assertThat(2L, is(votes));
    }

    @Test
    public void putVoteTest() throws Exception {

        mockMvc.perform(put(REST_URL + "/bar/" + TEST_BAR_1.getId())
                .with(userHttpBasic(USER))
        )
                .andDo(print())
                .andExpect(status().isOk());

    }

}
