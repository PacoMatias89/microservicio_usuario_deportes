package com.pacomolina.FeignClient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pacomolina.model.Sport;

@FeignClient(name="sport-server")
@RequestMapping("/sport")
public interface SportFeignClient {

	@PostMapping()
	Sport save(@RequestBody Sport sport);
	
	@GetMapping("/user/{userId}")
	List<Sport>getSportByUserId(@PathVariable long userId);
	
	@DeleteMapping()
	Sport delete();
	
}
