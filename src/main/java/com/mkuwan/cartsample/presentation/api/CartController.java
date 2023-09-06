package com.mkuwan.cartsample.presentation.api;

import com.mkuwan.cartsample.presentation.request.PutCartItemRequest;
import com.mkuwan.cartsample.presentation.viewmodel.CartItemViewModel;
import com.mkuwan.cartsample.presentation.viewmodel.CartViewModel;
import com.mkuwan.cartsample.usecase.service.CartUseCaseService;
import com.mkuwan.cartsample.usecase.service.ICartUseCaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/cart")
public class CartController {
    private final ICartUseCaseService cartUseCaseService;

    public CartController(CartUseCaseService cartUseCaseService) {
        this.cartUseCaseService = cartUseCaseService;
    }

    @GetMapping
    public ResponseEntity<String> getSample(){
        return ResponseEntity.ok()
                .body("サンプルですよ");
    }

    @PostMapping(path = "/test")
    public ResponseEntity<String> test(@RequestBody CartItemViewModel item){
        if(item.getItemPrice() > 2000)
            return ResponseEntity.badRequest()
                    .body("高すぎる(T.T)");

        return ResponseEntity.ok()
                .body(item.getItemName());
    }

    @PostMapping(path = "/put")
    public ResponseEntity<CartViewModel> putItem(@RequestBody PutCartItemRequest request){
        try {
            var result = CartViewModel.fromDto(cartUseCaseService
                    .putItemIntoCart(request.getCartId(), request.getBuyerId(), request.toDto()));

            return ResponseEntity.ok()
                    .body(result);

        } catch (Exception ex){
            CartViewModel response = new CartViewModel(request.getCartId(), request.getBuyerId(),
                    null, ex.getMessage());
            return ResponseEntity.badRequest()
                    .body(response);
        }
    }
}
