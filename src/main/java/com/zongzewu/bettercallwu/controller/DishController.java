package com.zongzewu.bettercallwu.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zongzewu.bettercallwu.common.R;
import com.zongzewu.bettercallwu.dto.DishDto;
import com.zongzewu.bettercallwu.entity.Category;
import com.zongzewu.bettercallwu.entity.Dish;
import com.zongzewu.bettercallwu.service.CategoryService;
import com.zongzewu.bettercallwu.service.DishFlavorService;
import com.zongzewu.bettercallwu.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * dish management
 */
@RestController
@RequestMapping("/dish")
@Slf4j
public class DishController {
    @Autowired
    private DishService dishService;
    @Autowired
    private DishFlavorService dishFlavorService;
    @Autowired
    private CategoryService categoryService;

    /**
     * add new dish
     * @param dishDto
     * @return
     */
    @PostMapping
    public R<String> save(@RequestBody DishDto dishDto){
      log.info(dishDto.toString());

      dishService.saveWithFlavor(dishDto);
        return R.success("Successfully added new dish");
    }

    /**
     * Handles GET requests to retrieve a paginated list of dishes.
     *
     * @param page      the current page number to retrieve
     * @param pageSize  the number of items per page
     * @param name      an optional search parameter for filtering dishes by name
     * @return          a response containing the paginated list of dishes with additional details
     */
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name) {
        // Create a page object for dishes based on the requested page number and page size
        Page<Dish> pageInfo = new Page<>(page, pageSize);
        // Create a separate page object for transferring DishDto objects
        Page<DishDto> dishDtoPage = new Page<>();

        // Construct a query wrapper with optional name filtering and descending order sorting by update time
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(name != null, Dish::getName, name);
        queryWrapper.orderByDesc(Dish::getUpdateTime);

        // Query the database for the requested page of dishes
        dishService.page(pageInfo, queryWrapper);

        // Copy the basic properties from the original page to the DTO page, excluding the records
        BeanUtils.copyProperties(pageInfo, dishDtoPage, "records");

        // Convert the list of Dish entities into a list of DishDto objects
        List<Dish> records = pageInfo.getRecords();
        List<DishDto> list = records.stream().map((item) -> {
            DishDto dishDto = new DishDto();

            // Copy properties from the Dish entity to the DishDto object
            BeanUtils.copyProperties(item, dishDto);

            // Fetch and set the category name using the categoryId from the Dish entity
            Long categoryId = item.getCategoryId();
            Category category = categoryService.getById(categoryId);

            if(category != null){
                String categoryName = category.getName();
                dishDto.setCategoryName(categoryName);
            }

            return dishDto;
        }).collect(Collectors.toList());

        // Set the list of DishDto objects as the records for the DTO page
        dishDtoPage.setRecords(list);

        // Return the paginated result wrapped in a success response
        return R.success(dishDtoPage);
    }

    /**
     *get dish info and it's flavor info by id
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R<DishDto> get(@PathVariable Long id ){
        DishDto dishDto = dishService.getByIdWithFlavor(id);
        return R.success(dishDto);
    }

    /**
     * modify dish
     * @param dishDto
     * @return
     */
    @PutMapping
    public R<String> modify(@RequestBody DishDto dishDto){
        log.info(dishDto.toString());

        dishService.updateWithFlavor(dishDto);
        return R.success("Successfully modified this dish");
    }

    /**
     * query dish info by dish data
     * @param dish
     * @return
     */
    @GetMapping("/list")
    public R<List<Dish>> list(Dish dish){

        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(dish.getCategoryId() != null, Dish::getCategoryId,dish.getCategoryId());
        queryWrapper.eq(Dish::getStatus, 1);
        //sorting method
        queryWrapper.orderByAsc(Dish::getSort).orderByDesc(Dish::getUpdateTime);

        List<Dish> list = dishService.list(queryWrapper);

        return R.success(list);
    }
}
