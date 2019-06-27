package com.tedu.sp09.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.tedu.sp01.pojo.Item;
import com.tedu.web.util.JsonResult;

//通知指定服务id,可以知道，向哪一台主机来转发调用
@FeignClient(name="item-service",fallback = ItemFeignServiceFB.class)
public interface ItemFeignService {
	/**
	 * feign利用springMVC注解来反向生成访问路径
	 * 	根据@PathVariable配置，把参数数据拼接到路径当中
	 * 	假设参数是"123",则路径为http://localhost:8001/123
	 * 	向拼接的路径，来转发调用
	 * @param orderId
	 * @return
	 */
	@GetMapping("/{orderId}")
	JsonResult<List<Item>> getItems(@PathVariable String orderId);

	/**
	 * 	根据配置，拼接下面的路径，并向下面的路径转发请求，在协议体中携带商品参数
	 * @param items
	 * @return
	 */
	@PostMapping("/decreaseNumber")
	JsonResult decreaseNumber(@RequestBody List<Item> items);
}

