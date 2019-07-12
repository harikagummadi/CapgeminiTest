package com.src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/sqrt")
public class SqrtNumbers {
	@GET
	@Path("/[arr]")
	@Produces(MediaType.TEXT_HTML)
	public Response getSqrt(@PathParam("arr")int[] intArr)
	{
		int[] sorted = IntStream.of(intArr)
	            .boxed()
	            .sorted(Comparator.reverseOrder())
	            .mapToInt(i -> i)
	            .toArray();
	    
	    IntStream stream = Arrays.stream(sorted);
	    
	    int[] highest = stream.limit(3).toArray();
	    List<Integer> list = new ArrayList<>(highest.length);

		for (int i : highest) {
			list.add(Integer.valueOf(i));
		}
	    
	    Function<Integer, Integer> square = x -> x * x;
	    //list.stream().map(square).forEach(x -> System.out.println(x));
	    
	    Integer sum = list.stream().map(square).reduce(0, (a, b) -> a + b);
	    
	    //System.out.println("square root : "+Math.sqrt(sum));

		Double sqrt = Math.sqrt(sum);
		String msg = String.format("getSqrt==> arr: %d, sqrt: %10.4f", intArr, sqrt);
		
		    //logger.info(msg);
		
		     
		    return Response.status(200).entity(msg).build();
	}

}
