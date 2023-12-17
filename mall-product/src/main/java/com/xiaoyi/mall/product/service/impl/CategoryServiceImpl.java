package com.xiaoyi.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xiaoyi.mall.product.constanst.CategoryConstants;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoyi.mall.common.utils.PageUtils;
import com.xiaoyi.mall.common.utils.Query;

import com.xiaoyi.mall.product.dao.CategoryDao;
import com.xiaoyi.mall.product.entity.CategoryEntity;
import com.xiaoyi.mall.product.service.CategoryService;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CategoryEntity> listTree() {
        // 查找所有的分类列表
        List<CategoryEntity> allCategoryList = baseMapper.selectList(null);
        List<CategoryEntity> result = allCategoryList.stream()
                // 过滤根菜单
                .filter(categoryEntity -> categoryEntity.getParentCid().equals(CategoryConstants.ROOT_ID))
                // 设置子菜单数据
                .map(rootCategory -> rootCategory.setChildren(getChildren(rootCategory,allCategoryList)))
                .sorted(Comparator.comparing(CategoryEntity::getSort,Comparator.nullsLast(Comparator.naturalOrder())))
                .collect(Collectors.toList());
        return result;
    }

    /**
     * 递归查找所有菜单的子菜单
     * @param rootCategory
     * @param allCategoryList
     * @return
     */
    private List<CategoryEntity> getChildren(CategoryEntity rootCategory, List<CategoryEntity> allCategoryList) {
        List<CategoryEntity> children = allCategoryList.stream()
                // 过滤有子数据列表的菜单
                .filter(categoryEntity -> rootCategory.getCatId().equals(categoryEntity.getParentCid()))
                // 递归设置菜单的子数据列表
                .map(categoryEntity -> categoryEntity.setChildren(getChildren(categoryEntity, allCategoryList)))
                .sorted(Comparator.comparing(CategoryEntity::getSort,Comparator.nullsLast(Comparator.naturalOrder())))
                .collect(Collectors.toList());
        return children;
    }

}