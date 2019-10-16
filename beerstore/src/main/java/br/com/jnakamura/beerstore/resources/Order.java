package br.com.jnakamura.beerstore.resources;

import br.com.jnakamura.beerstore.models.OrderItemsId;
import br.com.jnakamura.beerstore.models.OrderItemsModel;
import br.com.jnakamura.beerstore.models.OrderModel;
import br.com.jnakamura.beerstore.repository.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/orders")
public class Order {

    @Autowired
    private Orders ordersRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrderModel> getOrders() {

        return ordersRepository.findAll(Sort.by("orderDate"));

    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public OrderModel postOrders(@Valid @RequestBody OrderModel newOrder) {

        Set<OrderItemsModel> items = newOrder.getItems();

        newOrder.setItems(null);

        OrderModel savedOrder = ordersRepository.save(newOrder);

        savedOrder.setItems(items);

        savedOrder.getItems().iterator().forEachRemaining(item -> {
            item.setOrder(savedOrder);
            OrderItemsId orderItemsId = new OrderItemsId();
            orderItemsId.setBeerId(item.getBeers().getId());
            orderItemsId.setOrderId(savedOrder.getId());
            item.setId(orderItemsId);

        });


        return ordersRepository.save(savedOrder);

    }


}
