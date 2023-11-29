package bookstore.controller;

import bookstore.dto.cart.CartItemResponseDto;
import bookstore.dto.cart.CreateCartItemRequestDto;
import bookstore.dto.cart.UpdateCartItemRequestDto;
import bookstore.dto.cart.CartResponseDto;
import bookstore.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Cart", description = "Endpoints for managing books in a cart")
@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
@Validated
public class CartController {
    private final CartService cartService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('USER')")
    @Operation(description = "Add a book to a cart")
    CartResponseDto addCartItem(
            @RequestBody @Valid CreateCartItemRequestDto addDto,
            Authentication authentication
    ) {
        return cartService.addCartItem(addDto, authentication.getName());
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('USER')")
    @Operation(description = "Get cart of an authorized user")
    CartResponseDto getAllCartItems(Authentication authentication) {
        return cartService.getCart(authentication.getName());
    }

    @PutMapping("/{cartItemId}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('USER')")
    @Operation(description = "Update amount of books in cart by its id")
    CartItemResponseDto updateCartItem(
            @PathVariable @Positive Long cartItemId,
            @RequestBody @Valid UpdateCartItemRequestDto updateDto,
            Authentication authentication
    ) {
        return cartService.updateCartItem(cartItemId, updateDto, authentication.getName());
    }

    @DeleteMapping("/{cartItemId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('USER')")
    @Operation(description = "Delete a cartItem from cart")
    void deleteCartItem(
            @PathVariable @Positive Long cartItemId,
            Authentication authentication
    ) {
        cartService.deleteCartItem(cartItemId, authentication.getName());
    }
}
